/*
 * Any use of the Material is governed by the terms of the actual license
 * agreement between LeanTaaS Inc. and the user.
 * Copyright 2014 LeanTaaS Inc., Sunnyvale CA USA.
 * All rights reserved. Any rights not expressly granted herein are reserved.
 */

package api.results;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public interface Result extends Serializable {
}
