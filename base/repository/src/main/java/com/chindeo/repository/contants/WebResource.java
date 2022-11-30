package com.chindeo.repository.contants;

import com.chindeo.repository.mmkv.impl.SettingHostCache;

public class WebResource {

    public static class Image {

        private static final String imagePath = "/static/image/";

        private static String basePath() {
            return SettingHostCache.cacheFormatWebResHost();
        }

        private static String imagePath() {
            return basePath() + imagePath;
        }

        public static String logo() {
            return imagePath() + "logo.png";
        }

        /**
         * 接口返回的uri 再拼接
         * @param uri
         * @return
         */
        public static String getImagePath(String uri){
            return basePath() + uri;
        }

    }
}
