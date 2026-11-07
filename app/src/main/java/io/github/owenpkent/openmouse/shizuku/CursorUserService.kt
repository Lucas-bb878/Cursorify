package io.github.owenpkent.openmouse.shizuku

class CursorUserService : ICursorService.Stub() {
    override fun ping(): String {
        return "Cursorify conectado!"
    }
}
