package com.example.introspringai.basics;

import org.springframework.ai.image.ImageGeneration;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.introspringai.utils.FileUtils.saveImageToFile;

@RestController
@RequestMapping("/api/image-generation")
public class ImageModelController {

    private final ImageModel imageModel;

    public ImageModelController(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    @GetMapping
    public String generateImageWithUrl() {
        ImageOptions imageOptions = getChatOptions("url");
        ImagePrompt prompt = new ImagePrompt("generate the most highest mountain in Africa back in 1900",
                imageOptions);
        ImageGeneration response = this.imageModel.call(prompt).getResult();
        System.out.println(response.getOutput().getUrl());
        return response.getOutput().getUrl();

    }

    private static ImageOptions getChatOptions(String responseFormat) {
        return OpenAiImageOptions.builder()
                .withQuality("hd")
                .withN(1)
                .withModel("dall-e-3")
                .withResponseFormat(responseFormat)
                .withHeight(1024)
                .withWidth(1024)
                .build();
    }

    @GetMapping("/save-in-file")
    public void generateImageAndSave() {
        ImageOptions imageOptions = getChatOptions("b64_json");
        ImagePrompt prompt = new ImagePrompt("Generate an image of the highest mountain in Africa back in year 1900",
                imageOptions);
        String imageData = this.imageModel
                .call(prompt)
                .getResult()
                .getOutput()
                .getB64Json();
        try {
            saveImageToFile(imageData);
            System.out.println("Image saved successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
