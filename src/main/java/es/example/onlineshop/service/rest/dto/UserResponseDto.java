package es.example.onlineshop.service.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class UserResponseDto implements Serializable {

  private Integer page;

  @JsonProperty("per_page")
  private Integer perPage;

  private Integer total;

  @JsonProperty("total_pages")
  private Integer totalPages;

  private List<UserDto> data;

}
