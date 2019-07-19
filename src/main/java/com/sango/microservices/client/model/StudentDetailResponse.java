package com.sango.microservices.client.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetailResponse {
	private Student student;
	private boolean isFromLocalDataStore;
}
