package dev.android.kevin.project.model;

import android.location.Location;

import java.util.List;

/**
 * Created by kevinsun on 2/21/18.
 */

public class PlaceSearchBean {

    private String next_page_token;

    private List<Results> results;

    private String status;

    public String getNext_page_token() {
        return next_page_token;
    }

    public void setNext_page_token(String next_page_token) {
        this.next_page_token = next_page_token;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class Results {

        private Geometry geometry;

        private String icon;

        private String name;

        private Opening_hours opening_hours;

        private List<Photos> photos;

        private String place_id;

        private int price_level;

        private int rating;

        private List<String> types;

        private String vicinity;


        public Geometry getGeometry() {
            return geometry;
        }

        public void setGeometry(Geometry geometry) {
            this.geometry = geometry;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Opening_hours getOpening_hours() {
            return opening_hours;
        }

        public void setOpening_hours(Opening_hours opening_hours) {
            this.opening_hours = opening_hours;
        }

        public List<Photos> getPhotos() {
            return photos;
        }

        public void setPhotos(List<Photos> photos) {
            this.photos = photos;
        }

        public String getPlace_id() {
            return place_id;
        }

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }

        public int getPrice_level() {
            return price_level;
        }

        public void setPrice_level(int price_level) {
            this.price_level = price_level;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public String getVicinity() {
            return vicinity;
        }

        public void setVicinity(String vicinity) {
            this.vicinity = vicinity;
        }

        public static class Photos {
            private int height;

            private String photo_reference;

            private int width;

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public String getPhoto_reference() {
                return photo_reference;
            }

            public void setPhoto_reference(String photo_reference) {
                this.photo_reference = photo_reference;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }
        }

        public static class Opening_hours {

            private boolean open_now;

            public boolean isOpen_now() {
                return open_now;
            }

            public void setOpen_now(boolean open_now) {
                this.open_now = open_now;
            }
        }

        public static class Geometry {
            private Location location;

            public Location getLocation() {
                return location;
            }

            public void setLocation(Location location) {
                this.location = location;
            }

            public static class Location {
                private String lat;

                private String lng;

                public String getLat() {
                    return lat;
                }

                public void setLat(String lat) {
                    this.lat = lat;
                }

                public String getLng() {
                    return lng;
                }

                public void setLng(String lng) {
                    this.lng = lng;
                }
            }

        }

    }
}
