package com.example

import javax.validation.constraints.NotBlank
import jakarta.inject.Singleton
import java.util.*

@Singleton
open class UppercaseTransformer : NameTransformer {
    override fun transform(@NotBlank name: String): String {
        return name.uppercase(Locale.getDefault())
    }
}
