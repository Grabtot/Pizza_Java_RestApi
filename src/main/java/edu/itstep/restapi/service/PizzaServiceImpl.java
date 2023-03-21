package edu.itstep.restapi.service;

import edu.itstep.restapi.entity.Pizza;
import edu.itstep.restapi.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import javax.imageio.ImageIO;

@Service
public class PizzaServiceImpl implements PizzaService {

    @Autowired
    private PizzaRepository repository;

     @Value("${images.pizza.path}")
    private String pizzaPath;

    @Override
    public List<Pizza> getAll() {
        List<Pizza> pizzas = repository.findAll();
        for (Pizza pizza: pizzas) {
            pizza = codeImage(pizza);
        }
        return pizzas;
    }

    @Override
    public Pizza getById(int id) {
        Pizza pizza = repository.findById(id).orElse(null);
        return codeImage(pizza);
    }

    @Override
    public Pizza create(Pizza pizza) {
        return repository.save(decodeImage(pizza));
    }

    @Override
    public Pizza update(Pizza pizza) {

        return repository.save( decodeImage(pizza));
    }

    @Override
    public ResponseEntity<?> deleteById(int id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private Pizza codeImage(Pizza pizza) {
        String photoPath = pizza.getPhoto();

        byte[] byteArray = null;
        try {
            BufferedImage image = ImageIO.read(new File(photoPath));
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ImageIO.write(image, "png", stream);
            byteArray = stream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (byteArray != null) {
            String base64EncodedPhoto = Base64.getEncoder().encodeToString(byteArray);
            pizza.setPhoto(base64EncodedPhoto);
        }
        return pizza;
    }

    private Pizza decodeImage(Pizza pizza) {
        String base64EncodedPhoto = pizza.getPhoto();
        if (base64EncodedPhoto != null) {
            byte[] photoBytes = Base64.getDecoder().decode(base64EncodedPhoto);
            String photoFileName = pizza.getName() + ".png";
            String photoPath = pizzaPath + photoFileName;
            try {
                FileOutputStream fos = new FileOutputStream(photoPath);
                fos.write(photoBytes);
                fos.close();
                pizza.setPhoto(photoPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return pizza;
    }
}
