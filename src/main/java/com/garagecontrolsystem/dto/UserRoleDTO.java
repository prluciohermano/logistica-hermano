package com.garagecontrolsystem.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class UserRoleDTO {
	
	private UUID idUser;
	
	private List<UUID> idsRoles;

}
