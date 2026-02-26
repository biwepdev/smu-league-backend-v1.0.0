package com.smu.Post.core.dto;

import java.io.Serializable;

public record MessageResponse(boolean success, String message) implements Serializable {
}
