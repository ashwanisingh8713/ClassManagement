package com.indusjs.cm.di

import com.indusjs.cm.app.presentations.screens.home.tab1.Tab1_ProjectsViewModel
import com.indusjs.cm.app.viewmodels.login.SignInViewModel
import com.indusjs.cm.app.presentations.screens.login.SignUpViewModel
import com.indusjs.cm.app.viewmodels.profile.EditProfileViewModel
import com.indusjs.cm.app.viewmodels.profile.GetProfileViewModel
import com.indusjs.cm.data.repo.LoginRepoImpl
import com.indusjs.cm.data.repo.ProfileRepoImpl
import com.indusjs.cm.data.repo.ProjectRepoImpl
import com.indusjs.cm.domain.repo.IProjectRepo
import com.indusjs.cm.domain.repo.IUserRepository
import com.indusjs.cm.domain.usecase.login.ForgotPasswordUseCase
import com.indusjs.cm.domain.usecase.login.SignInUseCase
import com.indusjs.cm.domain.usecase.login.SignUpUseCase
import com.indusjs.cm.domain.usecase.profile.EditProfileUseCase
import com.indusjs.cm.domain.usecase.profile.GetProfileUseCase
import com.indusjs.cm.domain.usecase.projects.ProjectUseCase
import com.indusjs.cm.repository.ILoginRepo
import com.indusjs.cm.repository.IProfileRepo
import com.indusjs.cm.repository.UserRepositoryImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.Dispatchers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.qualifier.named
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
//            baseUrlModule,
            dispatcherModule,
            coroutineScopeModule
            //platformModule()
        )
    }



val viewModelModule: Module = module {
    factory { SignInViewModel(get(), get()) }
    factory { Tab1_ProjectsViewModel(get(), get()) }
    factory { SignUpViewModel(get()) }
    factory { EditProfileViewModel(get()) }
    factory { GetProfileViewModel(get()) }
}

val useCasesModule: Module = module {
    factory { ForgotPasswordUseCase(get(), get()) }
    factory { SignInUseCase(get(), get()) }
    factory { SignUpUseCase(get(), get()) }
    factory { EditProfileUseCase(get(), get()) }
    factory { GetProfileUseCase(get(), get()) }
    factory { ProjectUseCase(get(), get()) }
}

val baseUrlModule:Module = module {
//    single<String>(named("BaseUrl")) { "https://rickandmortyapi.com" }
    single<String>(named("BaseUrl")) { "https://rickandmortyapi.com" }
    single<String>(named("thg")) {  "https://app.thehindu.com/hindu/service/api_v1.006" }
}

val repositoryModule = module {
    single<IUserRepository> { UserRepositoryImpl(get(), get()) }
    single<ILoginRepo> {LoginRepoImpl(get() , get()) }
    single<IProfileRepo> { ProfileRepoImpl(get(), get()) }
    single<IProjectRepo> { ProjectRepoImpl(get(), get()) }
}

val dispatcherModule = module {
    factory { Dispatchers.Default }
}

val coroutineScopeModule = module {
    factory { CoroutineScope(Dispatchers.Default) }
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
                level = LogLevel.HEADERS
                filter { request ->
                    request.url.host.contains("ktor.io")
                }
                sanitizeHeader { header ->
                    header == HttpHeaders.Authorization
                }
            }
        }
    }

    single { "http://192.168.1.10:8080" }
}

fun initKoin(module: Module) {
    val koinApp = initKoin {}
    koinApp.modules(module)
}