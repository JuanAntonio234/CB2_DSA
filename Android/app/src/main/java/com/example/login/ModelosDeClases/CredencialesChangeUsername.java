package com.example.login.ModelosDeClases;

public class CredencialesChangeUsername
{
        private String username;
        private String newUsername;
        private String password;

        public CredencialesChangeUsername(String username,String newUsername,String password){
            this.username = username;
            this.newUsername = newUsername;
            this.password = password;
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNewUsername() {
            return this.newUsername;
        }

        public void setNewUsername(String newUsername) {
            this.newUsername = newUsername;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
}
