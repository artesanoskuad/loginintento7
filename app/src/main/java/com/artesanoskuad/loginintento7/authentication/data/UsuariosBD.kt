package com.artesanoskuad.loginintento7.authentication.data

object UsuariosBD {

    fun getUsuarios(): List<Usuario> {
        val usuarios = ArrayList<Usuario>()
        usuarios.add(crearUsuario("admin", "admin", "Nombre Completo Del Usuario"))
        usuarios.add(crearUsuario("goku", "goku", "Nombre Completo Del Usuario"))
        usuarios.add(crearUsuario("vegeta", "vegeta", "Nombre Completo Del Usuario"))
        usuarios.add(crearUsuario("homero", "homero", "Homer Jay Simpson"))
        return usuarios
    }

    fun loginConUsuarioYPassword(user: String, password: String): Usuario? {
        var usuarioRetorno : Usuario? = null
        getUsuarios().forEach { usuario ->
            if (usuario.password == password && usuario.usuario == user) {
                usuarioRetorno = usuario
                return@forEach
            }
        }
        return usuarioRetorno
    }

    private fun crearUsuario(usuario: String, password: String, nombreCompleto: String) =
        Usuario(usuario, nombreCompleto, password)
}