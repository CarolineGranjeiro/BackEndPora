
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

        if (conteudo == null || conteudo.isBlank()) {
            throw new IllegalArgumentException(
                    "Informe um conteúdo válido para gerar o QR Code."
            );
        }

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
