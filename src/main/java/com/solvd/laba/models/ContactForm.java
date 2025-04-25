package com.solvd.laba.models;

public class ContactForm {
    private final String name;
    private final String email;
    private final String subject;
    private final String message;
    private final String filePath;

    private ContactForm(ContactFormBuilder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.subject = builder.subject;
        this.message = builder.message;
        this.filePath = builder.filePath;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getSubject() { return subject; }
    public String getMessage() { return message; }
    public String getFilePath() { return filePath; }

    public static class ContactFormBuilder {
        private String name;
        private String email;
        private String subject;
        private String message;
        private String filePath;

        public ContactFormBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ContactFormBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public ContactFormBuilder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public ContactFormBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        public ContactFormBuilder setFilePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public ContactForm build() {
            return new ContactForm(this);
        }
    }
}
