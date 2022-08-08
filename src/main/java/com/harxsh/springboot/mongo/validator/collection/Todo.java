package com.harxsh.springboot.mongo.validator.collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.harxsh.springboot.mongo.validator.config.Views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "todo")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Todo {

    @Id
    @JsonView(Views.Internal.class)
    @JsonIgnore
    private String id;

    @JsonView(Views.Public.class)
    @NotNull(message = "Title is required")
    private String title;

    @JsonView(Views.Public.class)
    @NotNull(message = "Description is required")
    private String description;

    @JsonView(Views.Public.class)
    @NotNull(message = "Status is required")
    private String status;

    @JsonView(Views.Internal.class)
    @JsonIgnore
    @Field
    private Date createdAt;

    @JsonView(Views.Internal.class)
    @JsonIgnore
    private Date modifiedAt;
}