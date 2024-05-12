package com.claudiocarige.googleiagemini.infra.controllers;

import com.claudiocarige.googleiagemini.core.domain.entities.GeminiRequest;
import com.claudiocarige.googleiagemini.core.usecases.interfaces.GeminiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/root/criar-prompt")
@Tag(name = "Criador de Prompt", description = "  --  Aqui você pode criar seu prompt para interagir com o Gemini IA da Google!.")
public class RootController {

    private final GeminiService geminiService;

    public RootController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping
    @Operation(summary = "Criar Prompt", description = "Esta **API** criará um prompt para iteração com o " +
            "**Google Gemini AI**. \n\n" +
            "Com o atributo ''tipo'' você poderá escolher uma estrutura de saida, ou seja, um texto, um artigo, uma frase, " +
            "uma pergunta, um comentário...\n\n" +
            "Com o atributo ''sobre'' você pode direcionar para o tema desejado, por exemplo falar sobre: " +
            "linguagem Java, Gemini, qualquer tema da sua preferencia.\n\n" +
            "Para especificar melhor, vou exemplificar:\n\n" +
            "* **''tipo''** : ''Artigo''\n\n" +
            "* **''sobre''** : ''Linguagem java''\n\n" +
            "**Sobre a configuração de Criatividade temos:**\n\n" +
            "* **''temperature''** -> controla o quão será criativo de 0.0 a 1.0\n" +
            "* **''maxOutputTokens''** ->  Um token tem cerca de quatro caracteres. 100 tokens correspondem a " +
            "aproximadamente 60 a 80 palavras\n" +
            "* **''topK''** -> Muda a forma como o modelo seleciona tokens para saída. O valor topK de 1 indica " +
            "que o token selecionado é o mais provável entre todos no vocabulário do modelo (também chamado de " +
            "decodificação gananciosa), enquanto topK de 3 significa que o próximo token é selecionado entre " +
            "os três mais prováveis de usar a temperatura\n" +
            "* **''topP''** -> Os tokens são selecionados do mais ao menos provável até que a soma das" +
            " probabilidades seja igual ao valor de topP\n\n" +
            "**Fique a vontade para testar a API**",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                                    schema = @Schema(implementation = GeminiRequest.class))
                    ),
                    @ApiResponse(responseCode = "204", description = "No content", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
            })
    public ResponseEntity<String> getRoot(@RequestBody GeminiRequest geminiRequest) throws JsonProcessingException {
        return ResponseEntity.ok().body(geminiService.generate(geminiRequest));
    }
}
