package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;

public class TextGraphicsConverterImpl implements TextGraphicsConverter {
    private double maxRatio;
    private int maxWidth;
    private int maxHeight;
    private TextColorSchema schema = new BaseColorSchema();

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));

        double ratio = (double) img.getWidth() / img.getHeight();
        if (maxRatio > 0 && ratio > maxRatio) {
            throw new BadImageSizeException(ratio, maxRatio);
        }

        int newWidth = img.getWidth();
        int newHeight = img.getHeight();

        boolean widthLimited = maxWidth > 0 && newWidth > maxWidth;
        boolean heightLimited = maxHeight > 0 && newHeight > maxHeight;

        if (widthLimited || heightLimited) {
            double widthRatio = widthLimited ? (double) maxWidth / newWidth : Double.MAX_VALUE;
            double heightRatio = heightLimited ? (double) maxHeight / newHeight : Double.MAX_VALUE;

            double scale = Math.min(widthRatio, heightRatio);

            newWidth = (int) (newWidth * scale);
            newHeight = (int) (newHeight * scale);
        }

        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);

        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);

        WritableRaster raster = bwImg.getRaster();
        StringBuilder result = new StringBuilder();

        for (int h = 0; h < newHeight; h++) {
            for (int w = 0; w < newWidth; w++) {
                int color = raster.getPixel(w, h, new int[3])[0];
                char c = schema.convert(color);

                result.append(c).append(c);
            }
            result.append("\n");
        }

        return result.toString();
    }

    @Override
    public void setMaxWidth(int width) {
        this.maxWidth = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.maxHeight = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }
}
