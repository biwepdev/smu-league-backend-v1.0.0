package com.smu.village.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

@Schema(name = "Message response")
public record MessageResponse(Boolean success, String message) implements Serializable { }
