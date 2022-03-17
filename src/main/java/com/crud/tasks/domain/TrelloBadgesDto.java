package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrelloBadgesDto {
    @JsonProperty("votes")
    private int votes;
    @JsonProperty("attachmentsByType")
    private AttachmentByType attachments;
}
