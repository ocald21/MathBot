package dev.butter.mathbot.module

import com.authzee.kotlinguice4.KotlinModule
import com.authzee.kotlinguice4.multibindings.KotlinMultibinder
import kotlin.reflect.KClass

abstract class AddonModule(
    private vararg val classes: KClass<out Addon>
) : KotlinModule() {
    private lateinit var addonBinder: KotlinMultibinder<Addon>

    override fun configure() {
        addonBinder = KotlinMultibinder.newSetBinder(kotlinBinder)
        classes.forEach(::addBinding)
    }

    private fun addBinding(clazz: KClass<out Addon>) {
        addonBinder.addBinding().to(clazz.java)
    }
}

