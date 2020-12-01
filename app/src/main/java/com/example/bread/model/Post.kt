package com.example.bread.model

import com.google.firebase.auth.FirebaseUser

class Post {
    var nomeProduto: String = ""
    var categoria: String = ""
    var descricao: String = ""
    var imageUrl: String = ""
    var nome: String = "null"

    constructor(nomeProduto: String, categoria: String, descricao: String, imageUrl: String, nome: String){
        this.nomeProduto = nomeProduto
        this.categoria = categoria
        this.descricao = descricao
        this.imageUrl = imageUrl
        this.nome = nome
    }

    constructor(){}
}