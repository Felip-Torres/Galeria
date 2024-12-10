/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pruebagaleria;

/**
 *
 * @author Alumne
 */
import com.azure.storage.blob.*;
import com.azure.storage.blob.models.*;
import java.util.ArrayList;

public class AzureBlobService {
    private final BlobServiceClient blobServiceClient;
    private final BlobContainerClient containerClient;

    public AzureBlobService(String connectionString, String containerName) {
        // Inicializar el cliente del servicio de blobs
        blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
        containerClient = blobServiceClient.getBlobContainerClient(containerName);
    }

    public ArrayList<String> obtenerUrlsImagenes(String carpetaRemota) {
        ArrayList<String> imgUrls = new ArrayList<>();

        if (!containerClient.exists()) {
            System.out.println("El contenedor no existe.");
            return imgUrls;
        }

        for (BlobItem blobItem : containerClient.listBlobs()) {
            // Filtrar solo los blobs que están dentro de la carpeta remota y validar imágenes
            if (blobItem.getName().startsWith(carpetaRemota + "/") && Panel.imagenValida(blobItem.getName())) {
                BlobClient blobClient = containerClient.getBlobClient(blobItem.getName());
                // Obtener la URL del blob
                String url = blobClient.getBlobUrl();
                imgUrls.add(url);
            }
        }
        return imgUrls;
    }
}

