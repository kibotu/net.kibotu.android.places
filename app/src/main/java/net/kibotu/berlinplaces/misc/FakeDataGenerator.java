package net.kibotu.berlinplaces.misc;

import java.util.Random;

import static java.text.MessageFormat.format;

/**
 * Created by Nyaruhodo on 07.05.2016.
 */
public class FakeDataGenerator {

    public static String createRandomImageUrl() {
        return format("http://lorempixel.com/{0}/{1}/", new Random().nextInt(400) + 200, new Random().nextInt(200) + 400);
    }
}
