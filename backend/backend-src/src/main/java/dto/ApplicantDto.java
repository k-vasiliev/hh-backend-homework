package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import config.ApplicationConfig;

public class ApplicantDto {

   @JsonProperty("name")
   private String name;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public ApplicantDto(String name) {
      this.name = name;
   }

   public ApplicantDto() {}

}
