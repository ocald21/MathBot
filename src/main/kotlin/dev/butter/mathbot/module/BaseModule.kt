package dev.butter.mathbot.module

import com.authzee.kotlinguice4.KotlinModule
import com.authzee.kotlinguice4.multibindings.KotlinMultibinder
import kotlin.reflect.KClass

abstract class BaseModule : KotlinModule() {
    private lateinit var addonBinder: KotlinMultibinder<Addon>

    override fun configure() {
        addonBinder = KotlinMultibinder.newSetBinder(kotlinBinder)
    }

    protected fun addBinding(vararg clazz: KClass<out Addon>) =
        clazz.forEach { addonBinder.addBinding().to(it.java) }
}