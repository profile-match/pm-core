package org.profilematch.pmcore.config;

import javax.ws.rs.FormParam;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

/**
 *
 * @author antoine
 */
public class FileUploadForm {
    private byte[] filedata;

    public FileUploadForm() {}

    public byte[] getFileData() {
        return filedata;
    }

    @FormParam("filedata")
    @PartType("application/octet-stream")
    public void setFileData(final byte[] filedata) {
        this.filedata = filedata;
    }
}
