package com.indusjs.cm.di

import com.indusjs.cm.app.viewmodels.login.ForgotPasswordViewModel
import com.indusjs.cm.app.viewmodels.login.SignInViewModel
import com.indusjs.cm.app.screens.login.SignUpViewModel
import com.indusjs.cm.app.viewmodels.profile.EditProfileViewModel
import com.indusjs.cm.app.viewmodels.profile.GetProfileViewModel
import com.indusjs.cm.data.repo.LoginRepoImpl
import com.indusjs.cm.data.repo.ProfileRepoImpl
import com.indusjs.cm.domain.repo.IUserRepository
import com.indusjs.cm.domain.usecase.login.ForgotPasswordUseCase
import com.indusjs.cm.domain.usecase.login.SignInUseCase
import com.indusjs.cm.domain.usecase.login.SignUpUseCase
import com.indusjs.cm.domain.usecase.profile.EditProfileUseCase
import com.indusjs.cm.domain.usecase.profile.GetProfileUseCase
import com.indusjs.cm.repository.ILoginRepo
import com.indusjs.cm.repository.IProfileRepo
import com.indusjs.cm.repository.UserRepositoryImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import kotlinx.coroutines.Dispatchers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            viewModelModule,
            useCasesModule,
            repositoryModule,
            ktorModule,
            //mapperModule,
            dispatcherModule,
            //platformModule()
        )
    }

val viewModelModule: Module = module {
    factory { ForgotPasswordViewModel() }
    factory { SignInViewModel(get()) }
    factory { SignUpViewModel() }
    factory { EditProfileViewModel(get()) }
    factory { GetProfileViewModel(get()) }
}

val useCasesModule: Module = module {
    factory { ForgotPasswordUseCase(get(), get()) }
    factory { SignInUseCase(get(), get()) }
    factory { SignUpUseCase(get(), get()) }
    factory { EditProfileUseCase(get(), get()) }
    factory { GetProfileUseCase(get(), get()) }
}
val repositoryModule = module {
    single<IUserRepository> { UserRepositoryImpl(get(), get()) }
    single<ILoginRepo> {LoginRepoImpl(get(), get()) }
    single<IProfileRepo> { ProfileRepoImpl(get(), get()) }
}

val dispatcherModule = module {
    factory { Dispatchers.Default }
}

val ktorModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }

    single { "https://rickandmortyapi.com" }
}