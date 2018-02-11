package com.geniuses.newsclient.entity;

/**
 * Created by Sch on 2018/2/11.
 */

public class AppBean {

    /**
     * code : 0
     * message :
     * data : {"buildBuildVersion":"3","downloadURL":"https://www.pgyer.com/app/installUpdate/62af81a94e8838c8220a3d8681ab1c16?sig=nhUaP%2FcN6Ki%2ByeqrRGcQdqzOXFav%2FcVIOZEmWPLVLzNF4ZnssZRUG4h8%2BH2Gi1i%2F",
     * "buildVersionNo":"1",
     * "buildVersion":"1.2",
     * "buildShortcutUrl":"https://www.pgyer.com/Z215",
     * "buildUpdateDescription":""}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * buildBuildVersion : 3
         * downloadURL : https://www.pgyer.com/app/installUpdate/62af81a94e8838c8220a3d8681ab1c16?sig=nhUaP%2FcN6Ki%2ByeqrRGcQdqzOXFav%2FcVIOZEmWPLVLzNF4ZnssZRUG4h8%2BH2Gi1i%2F
         * buildVersionNo : 1
         * buildVersion : 1.2
         * buildShortcutUrl : https://www.pgyer.com/Z215
         * buildUpdateDescription :
         */

        private String buildBuildVersion;
        private String downloadURL;
        private String buildVersionNo;
        private String buildVersion;
        private String buildShortcutUrl;
        private String buildUpdateDescription;

        public String getBuildBuildVersion() {
            return buildBuildVersion;
        }

        public void setBuildBuildVersion(String buildBuildVersion) {
            this.buildBuildVersion = buildBuildVersion;
        }

        public String getDownloadURL() {
            return downloadURL;
        }

        public void setDownloadURL(String downloadURL) {
            this.downloadURL = downloadURL;
        }

        public String getBuildVersionNo() {
            return buildVersionNo;
        }

        public void setBuildVersionNo(String buildVersionNo) {
            this.buildVersionNo = buildVersionNo;
        }

        public String getBuildVersion() {
            return buildVersion;
        }

        public void setBuildVersion(String buildVersion) {
            this.buildVersion = buildVersion;
        }

        public String getBuildShortcutUrl() {
            return buildShortcutUrl;
        }

        public void setBuildShortcutUrl(String buildShortcutUrl) {
            this.buildShortcutUrl = buildShortcutUrl;
        }

        public String getBuildUpdateDescription() {
            return buildUpdateDescription;
        }

        public void setBuildUpdateDescription(String buildUpdateDescription) {
            this.buildUpdateDescription = buildUpdateDescription;
        }
    }
}
