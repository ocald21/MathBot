package dev.butter.mathbot.module

import com.google.inject.AbstractModule
import com.google.inject.multibindings.Multibinder

abstract class BaseModule : AbstractModule() {
    private lateinit var addonBinder: Multibinder<Addon>

    override fun configure() {
        addonBinder = Multibinder.newSetBinder(binder(), Addon::class.java)
    }

    protected fun addBinding(vararg clazz: Class<out Addon>) =
        clazz.forEach { addonBinder.addBinding().to(it) }
}