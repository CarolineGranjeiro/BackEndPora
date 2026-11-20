package com.inspecao.inspecao_dormentes.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class QrCodeService {

    public byte[] gerarImagem(String conteudo) {
        try {
            BitMatrix matriz = new MultiFormatWriter().encode(

                    conteudo,
                    BarcodeFormat.QR_CODE,
                    300,
                    300
            );

            ByteArrayOutputStream output = new ByteArrayOutputStream();

            MatrixToImageWriter.writeToStream(
                    matriz,
                    "PNG",
                    output
            );

            return output.toByteArray();

        } catch (WriterException | IOException e) {
            throw new IllegalStateException(
                    "Erro ao gerar imagem do QR Code.", e
            );
        }
    }
}