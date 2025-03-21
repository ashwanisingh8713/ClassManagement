package com.indusjs.cm.domain.model.login



data class SignInResponse(val created_at: String,
                          val email: String,
                          val password_hash: String,
                          val role: String,
                          val updated_at: String,
                          val username: String,
                        )




