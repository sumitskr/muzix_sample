package com.niiit.muzix.recommended;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Tracks {
	private List<Track> track;
//	@JsonAlias({"@attr"})
//	private Attr attr;
}
