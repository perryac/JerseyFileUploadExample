// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.fileuploads.web.resource;

import de.bytefish.fileuploads.handler.IFileUploadHandler;
import de.bytefish.fileuploads.model.files.HttpFile;
import de.bytefish.fileuploads.model.request.FileUploadRequest;
import de.bytefish.fileuploads.model.response.FileUploadResponse;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;


@Component
@Path("/files")
public class FileUploadResource  {

    private final IFileUploadHandler fileUploadHandler;

    @Autowired
    public FileUploadResource(IFileUploadHandler fileUploadHandler) {
        this.fileUploadHandler = fileUploadHandler;
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response fileUpload(@FormDataParam("title") String title,
                               @FormDataParam("description") String description,
                               @FormDataParam("file") InputStream stream,
                               @FormDataParam("file") FormDataContentDisposition fileDetail) {

        // Create the HttpFile:
        HttpFile httpFile = new HttpFile(fileDetail.getName(), fileDetail.getFileName(), fileDetail.getSize(), fileDetail.getParameters(), stream);

        // Create the FileUploadRequest:
        FileUploadRequest fileUploadRequest = new FileUploadRequest(title, description, httpFile);

        // Handle the File Upload:
        FileUploadResponse result = fileUploadHandler.handle(fileUploadRequest);

        return Response
                .status(200)
                .entity(result)
                .build();
    }
}
