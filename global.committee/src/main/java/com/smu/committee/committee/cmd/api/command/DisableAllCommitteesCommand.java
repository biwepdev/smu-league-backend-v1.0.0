package com.smu.committee.committee.cmd.api.command;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.Serializable;

@Tag(name = "Disable  all committees command")
public record DisableAllCommitteesCommand() implements Serializable {
}
