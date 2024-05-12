package com.claudiocarige.googleiagemini.core.usecases.impl;

import com.claudiocarige.googleiagemini.core.domain.entities.*;
import com.claudiocarige.googleiagemini.core.usecases.interfaces.GeminiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class GeminiServiceImpl implements GeminiService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();
    private final List<String> stringList = new ArrayList<>();
    private final List<String> stringRole = new ArrayList<>();

    @Value("${gemini.url}")
    private String google_key;

    public GeminiServiceImpl(){
        stringAdd();
    }

    public String generate(GeminiRequest geminiRequest) throws JsonProcessingException {

        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key="+google_key;
        stringList.add("com base nas informções anteriores, crie um prompt que solicite ao modelo de linguagem um(a) "
                        + geminiRequest.tipo()+" sobre o(a) " + geminiRequest.sobre());
        Root request = criarObjectRoot(geminiRequest);
        System.out.println("Content Body: " + request);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(request);
        System.out.println("Request Body: " + request);

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        var responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String jsonResponse = responseEntity.getBody();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(jsonResponse);
            JsonNode firstParts = jsonNode.at("/candidates/0/content/parts/0");

            System.out.println(firstParts.get("text"));
            System.out.println("Response: " + responseEntity.getBody());
            return limparString(firstParts.get("text").asText());
        } else {
            System.out.println("Erro na solicitação: " + responseEntity.getStatusCode());
            throw new RuntimeException("Algo deu errado!");
        }
    }

    private String limparString(String jsonResponse) {
        return jsonResponse
                .replaceAll("\\*", "")
                .replaceAll("\n\n", "")
                .replaceAll("\n\\*", "")
                .replaceAll("\\*\\*", "")
                .replaceAll("\\*\\*\\n\\n\\*", "")
                .replaceAll("\\n\\n\\*\\*", "")
                .replaceFirst("Prompt:", "");
    }

    private Root criarObjectRoot(GeminiRequest geminiRequest) {
        Root root = new Root();
        for(int i = 0; i < stringRole.size(); i++){
            Content content = criarObjetoContent(i);
            root.addContent(content);
        }
        root.setGenerationConfig(criarObjectGenerationConfig(geminiRequest));
        return root;
    }
    private Content criarObjetoContent(int i) {
        Part part = new Part();
        part.setText(stringList.get(i));
        System.out.println("Part para treste:  " + part.getText());
        Content content = new Content();
        content.addPart(part);
        content.setRole(stringRole.get(i));
        return content;
    }
    private GenerationConfig criarObjectGenerationConfig(GeminiRequest geminiRequest) {
        GenerationConfig generationConfig = new GenerationConfig();
        generationConfig.setTemperature(geminiRequest.configureCriatividade().getTemperature());
        generationConfig.setTopP(geminiRequest.configureCriatividade().getTopP());
        generationConfig.setTopK(geminiRequest.configureCriatividade().getTopK());
        generationConfig.setMaxOutputTokens(geminiRequest.configureCriatividade().getMaxOutputTokens());
        return generationConfig;
    }
    private void stringAdd() {
        stringList.add("como seria um prompt perfeito para um modelo de linguegem? escreva em portugues ");
        stringList.add("Um prompt perfeito para um modelo de linguagem deve: Ser claro e conciso: Fornecer" +
                " instruções precisas e fáceis de entender. Especificar o objetivo: Declarar claramente o que" +
                " se espera que o modelo produza. Fornecer contexto: Incluir informações relevantes que ajudem " +
                "o modelo a entender o propósito do prompt. Ser específico: Fornecer detalhes suficientes para " +
                "orientar o modelo na geração de uma resposta apropriada. Ser relevante: Garantir que o prompt " +
                "esteja relacionado ao conhecimento e às habilidades do modelo. Evitar ambiguidade: Usar linguagem " +
                "clara e inequívoca para evitar mal-entendidos. Fornecer exemplos: Incluir exemplos para ilustrar o" +
                " tipo de resposta desejada. Definir restrições: Especificar quaisquer limitações ou requisitos que " +
                "o modelo deva seguir. Ser gramaticalmente correto: Usar gramática e ortografia corretas para " +
                "facilitar o processamento do modelo. Ser informativo: Fornecer informações suficientes para que " +
                "o modelo possa gerar uma resposta abrangente e precisa.");
        stringRole.add("user");
        stringRole.add("model");
        stringRole.add("user");

    }

}
