package com.smu.committee.committee.cmd.api.command;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.Serializable;

@Tag(name = "Enable all committees command")
public record EnableAllCommitteesCommand() implements Serializable {
}
