/*
 *  Any use of the Material is governed by the terms of the actual license
 *  agreement between LeanTaaS Inc. and the user.
 *  Copyright 2010 LeanTaaS Inc., Sunnyvale CA USA.
 *  All rights reserved. Any rights not expressly granted herein are
 *  reserved.
 */
package api.filters

import play.api.mvc._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class AddResponseHeader extends Filter {
	/* Enable CORS Support in the web service */
	def apply(f: (RequestHeader) => Future[Result])(rh: RequestHeader): Future[Result] = {
		val result = f(rh)
		result.map(_.withHeaders("Access-Control-Allow-Origin" -> "*"))
	}
}