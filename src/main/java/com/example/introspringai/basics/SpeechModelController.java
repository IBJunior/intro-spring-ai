package com.example.introspringai.basics;

import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.introspringai.utils.FileUtils.writeSoundBytesToFile;

@RestController
@RequestMapping("/api/audio")
public class SpeechModelController {

    private final OpenAiAudioSpeechModel openAiAudioSpeechModel;

    public SpeechModelController(OpenAiAudioSpeechModel openAiAudioSpeechModel) {
        this.openAiAudioSpeechModel = openAiAudioSpeechModel;
    }

    @GetMapping("/text-to-speech")
    public void generateAudio() {
        OpenAiAudioSpeechOptions speechOptions = OpenAiAudioSpeechOptions.builder()
                .withModel("tts-1-hd")
                .withVoice(OpenAiAudioApi.SpeechRequest.Voice.ALLOY)
                .withResponseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
                .withSpeed(1.0f)
                .build();

        SpeechPrompt speechPrompt = new SpeechPrompt("Ceci est un exemple de transformation d'un texte en audio", speechOptions);
        SpeechResponse response = openAiAudioSpeechModel.call(speechPrompt);
        writeSoundBytesToFile(response.getResult().getOutput());
    }


}
