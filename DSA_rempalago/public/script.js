$(document).ready(function(){
var iniciado=false;
var tienda=false;
  $("#iniciar").hide();
  $("#edit").hide();
  $("#registro").hide();
  $("#tienda").hide();
  $("#editar-usuario").hide();
  $("#editar-contrasena").hide();
  $("#borrar-usuario").hide();
  $("#edit_buton").hide();
  $("#info_buton").hide();
  $("#borrar_buton").hide();
  $("#salir_buton").hide();
  $("#miperfil").hide();

   function ponerSaldo(){
    var saldo=sessionStorage.getItem('eurillos');
    $("#saldo").html('<h4>Saldo: '+saldo+'$</h4>');
   }
   function cambiarBotonDesplegable() {
       $("#inicio_buton").hide();
       $("#regis_buton").hide();
       $("#edit_buton").show();
       $("#info_buton").show();
       $("#borrar_buton").show();
       $("#salir_buton").show();

       var username=sessionStorage.getItem('username');
       $("#myNavbar .dropdown-toggle").html('MI PERFIL <br>' + username +' <span class="caret"></span>');
   }

   function showIniciarSection() {
      $("#inicio").hide();
      $("#test").hide();
      $("#iniciar").show();
      $("#registro").hide();
      $("#tienda").hide();
      $("#edit").hide();
      $("#edit_buton").hide();
             $("#info_buton").hide();
             $("#borrar_buton").hide();
   }
   function comenzarDeNuevo(){
         $("#inicio").hide();
         $("#test").show();
         $("#iniciar").hide();
         $("#registro").hide();
         $("#tienda").hide();
         $("#edit").hide();
         $("#miperfil").hide();
          $("#inicio_buton").show();
          $("#borrar-usuario").hide();
                $("#regis_buton").show();
                $("#edit_buton").hide();
                $("#info_buton").hide();
                $("#borrar_buton").hide();
                $("#myNavbar .dropdown-toggle").html('MI PERFIL <span class="caret"></span>');
                              iniciado = false;
         sessionStorage.removeItem('username');
                       sessionStorage.removeItem('password');
                       sessionStorage.removeItem('avatar');
                       sessionStorage.removeItem('eurillos');
                       sessionStorage.removeItem('points');
                       sessionStorage.removeItem('mail');
   }

   function mostrarAlerta(tipo, contenido) {
        $('#miAlerta').html(contenido);
        $('#miAlerta').removeClass().addClass('alert ' + 'alert-' + tipo);
        $('#miAlerta').fadeIn().delay(2000).fadeOut();
   }

   function generarProductoWEB(producto, index){
        return `
        <div class="col-md-4 custom-row-margin">
            <div class="card">
                <img src="${producto.image}" class="card-img-top" alt="${producto.nombre}" width="40" height="40">
                <div class="card-body">
                    <h5 class="card-title">${producto.nombre}</h5>
                    <p class="card-text">${producto.description}</p>
                    <p class="card-text">${producto.precio}$</p>
                    <a href="#" class="btn btn-primary btnComprar" data-index="${index}">Comprar</a>
                </div>
            </div>
        </div>
    `;
   }

   $("#inicio_buton").click(function(){
      showIniciarSection();
   });

  $("#tienda_button").click(function(){
      $("#inicio").hide();
      $("#test").hide();
      $("#registro").hide();
      $("#iniciar").hide();
      $("#tienda").show();
      $("#edit").hide();
      $("#borrar-usuario").hide();
      $("#editar-usuario").hide();
      $("#editar-contrasena").hide();
      $("#miperfil").hide();
    });

    $("#regis_buton").click(function(){
      $("#inicio").hide();
      $("#test").hide();
      $("#registro").show();
      $("#iniciar").hide();
      $("#edit").hide();
      $("#tienda").hide();
      $("#borrar-usuario").hide();
      $("#editar-usuario").hide();
      $("#editar-contrasena").hide();
      $("#miperfil").hide();
    });

  $("#edit_buton").click(function(){
        $("#inicio").hide();
        $("#test").hide();
        $("#registro").hide();
        $("#iniciar").hide();
        $("#tienda").hide();
        $("#edit").show();
        $("#borrar-usuario").hide();
        $("#editar-usuario").show();
        $("#editar-contrasena").show();
        $("#miperfil").hide();
      });

  $("#borrar_buton").click(function(){
        $("#inicio").hide();
        $("#test").hide();
        $("#registro").hide();
        $("#iniciar").hide();
        $("#edit").hide();
        $("#tienda").hide();
        $("#editar-usuario").hide();
        $("#editar-contrasena").hide();
        $("#borrar-usuario").show();
        $("#miperfil").hide();
      });


  $("#info_buton").click(function(){
        $("#inicio").hide();
        $("#test").hide();
        $("#registro").hide();
        $("#iniciar").hide();
        $("#tienda").hide();
        $("#miperfil").show();
        $("#edit").hide();
        $("#editar-usuario").hide();
        $("#editar-contrasena").hide();
        $("#borrar-usuario").hide();

      });
  $("#salir_buton").click(function(){
          comenzarDeNuevo();

        });



    $("#menu").click(function(){
      $("#iniciar").hide();
      $("#registro").hide();
      $("#inicio").show();
      $("#test").show();
      $("#tienda").hide();
      $("#editar-usuario").hide();
      $("#edit").hide();
      $("#editar-contrasena").hide();
      $("#borrar-usuario").hide();
      $("#miperfil").hide();
    });

      document.getElementById('btnRegister').addEventListener('click',function (){
      var username=document.getElementById('usr_reg').value;
      var email=document.getElementById('email_reg').value;
      var password=document.getElementById('pwd_reg').value;

      if(username==''){
        document.getElementBy('usernameReg-error').innerText='Please enter a username';
        return;
       }else{
        document.getElementById('usernameReg-error').innerText='';
       }
       if(email==''){
        document.getElementBy('emailReg-error').innerText='Please enter a email';
        return;
       }else{
        document.getElementById('emailReg-error').innerText='';
       }
       if(password==''){
        document.getElementBy('passwordReg-error').innerText='Please enter a password';
        return;
       }else{
        document.getElementById('passwordReg-error').innerText='';
       }
       alert ('Todo correcto, se puede enviar.')
    });

    $("#btnRegister").click(function(){

      var username=$("#usr_reg").val();
      var email=$("#email_reg").val();
      var password=$("#pwd_reg").val();

      var userData={
        username:username,
        email:email,
        password:password
      };

      fetch('http://localhost:8080/dsaApp/jugadores/register',{
        method:'POST',
        headers: {
          'Content-Type':'application/json'
        },
      body: JSON.stringify(userData)
      })
      .then(response=> response.json())
      .then(data => {
              console.log(data);
              if (data.success) {
                sessionStorage.setItem('username', username);
                mostrarAlerta('success', 'Registro successful!');
                showIniciarSection();
              } else {
                mostrarAlerta('danger', data.message);
              }
            })
            .catch(error => {
              console.error('Error', error);
              mostrarAlerta('danger', 'An error occurred. Please try again later.');
            });
    });

  $("#btnLogin").click(function(){
      var username=$("#usr_ini").val();
      var password=$("#pwd_ini").val();

      var userData={
        username:username,
        password:password
      };

      fetch('http://localhost:8080/dsaApp/jugadores/login',{
        method:'POST',
        headers:{
          'Content-Type':'application/json'
        },
        body:JSON.stringify(userData)
      })
      .then(response=> response.json())
      .then(data => {
          console.log(data);
          if (data.success) {
            sessionStorage.setItem('username', username);
            fetch('http://localhost:8080/dsaApp/jugadores/'+username,{
                    method:'GET',
                    headers:{
                      'Content-Type':'application/json'
                    },

                  })
                  .then(response=> response.json())
                  .then(data => {
                      console.log(data);
                      sessionStorage.setItem('password', data.password);
                      sessionStorage.setItem('mail', data.mail);
                      sessionStorage.setItem('points', data.points);
                      sessionStorage.setItem('eurillos', data.eurillos);
                      sessionStorage.setItem('avatar', data.avatar);
                      cambiarBotonDesplegable();
                      mostrarAlerta('success', 'Login successful!');
                      $("#iniciar").hide();
                      $("#inicio").show();
                      $("#test").show();
                      iniciado=true;
                      $("#usr_ini").val()=' ';
                      $("#pwd_ini").val()=' ';


                    })
                    .catch(error => {
                      console.error('Error', error);
                      mostrarAlerta('danger', 'later.');
                    });

          } else {
            mostrarAlerta('danger', data.message);
          }
        })
        .catch(error => {
          console.error('Error', error);
          mostrarAlerta('danger', 'An error occurred. Please try again later.');
        });
    });

  $("#btnEditUser").click(function(){

    var usernameJugadorRegistrado=sessionStorage.getItem('username');
    var newUsername = $("#new_usr").val();
    var password = $("#pwd_edit").val();

    var userData = {
       username:usernameJugadorRegistrado,
       newUsername: newUsername,
       password: password
    };
    console.log(userData);
    fetch('http://localhost:8080/dsaApp/jugadores/updateUsername',{
          method:'PUT',
          headers:{
            'Content-Type':'application/json'
          },
          body:JSON.stringify(userData)
        })
        .then(response=> response.json())
        .then(data => {
            console.log(data);
            if (data.success) {
              sessionStorage.setItem('username', newUsername);
              mostrarAlerta('success', 'Update successful!');
              var username = sessionStorage.getItem('username');
              $("#myNavbar .dropdown-toggle").html('MI PERFIL <br>' + username +' <span class="caret"></span>');
            } else {
              mostrarAlerta('danger', data.message);
            }
          })
          .catch(error => {
            console.error('Error', error);
            mostrarAlerta('danger', 'An error occurred. Please try again later.');
          });
     });


  $("#btnEditPassword").click(function(){
      var usernameJugadorRegistrado = sessionStorage.getItem('username');
      var password = $("#current_pwd").val();
      var newPassword = $("#new_pwd").val();
      var newPassword2 = $("#new_pwd2").val();

      var userData = {
         username: usernameJugadorRegistrado,
         password: password,
         newPassword: newPassword
      };
      if(newPassword==newPassword2){
      fetch('http://localhost:8080/dsaApp/jugadores/updatePassword',{
                method:'PUT',
                headers:{
                    'Content-Type':'application/json'
                },
                body:JSON.stringify(userData)
            })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if (data.success) {
                    mostrarAlerta('success', 'Contraseña actualizada correctamente.');
                    sessionStorage.setItem('password', newPassword);
                } else {
                    mostrarAlerta('danger', data.message);
                }
            })
            .catch(error => {
                console.error('Error', error);
                mostrarAlerta('danger', 'Se produjo un error. Por favor, inténtelo de nuevo más tarde.');
            });
            $("#current_pwd").val()='';
                  $("#new_pwd").val()='';
                  $("#new_pwd2").val()='';
      }
      else{
      mostrarAlerta('danger', 'Tienes que poner la misma contraseña.');
      $("#current_pwd").val()='';
                        $("#new_pwd").val()='';
                        $("#new_pwd2").val()='';
      }

  });

  $("#btnDeleteUser").click(function(){
      var usernameJugadorRegistrado = sessionStorage.getItem('username');

      var userData = {
         username: usernameJugadorRegistrado,
      };

      fetch('http://localhost:8080/dsaApp/jugadores/deleteUser/' + usernameJugadorRegistrado, {
          method: 'DELETE',
          headers: {
              'Content-Type': 'application/json'
          },

      })
      .then(response => response.json())
      .then(data => {
          console.log(data);
          if (data.success) {
              // Borrar la información de sesión después de borrar el usuario

              mostrarAlerta('success', 'Usuario borrado correctamente.');

              comenzarDeNuevo();

          } else {
              mostrarAlerta('danger', data.message);
          }
      })
      .catch(error => {
          console.error('Error', error);
          mostrarAlerta('danger', 'Se produjo un error. Por favor, inténtelo de nuevo más tarde.');
      });
  });



  $('#tienda_button').click(function(){
          if(iniciado== false){
          $("#tienda").hide();
                mostrarAlerta('danger', 'Tienes que iniciar sesión.');
          }
          else{

                ponerSaldo();


                            const listaProductosElement = document.getElementsByClassName('tienda_productos')[0];

                                      fetch('http://localhost:8080/dsaApp/tienda/todos')
                                          .then(response => response.json())
                                          .then(productos => {
                                              productos.forEach((producto, index) => {
                                              const productoWeb = generarProductoWEB(producto, index);

                                                  listaProductosElement.innerHTML += productoWeb;
                                              });
                                                $('.btnComprar').click(function(){
                                                	var index = $(this).data('index');
                                                        var username = $('#usr_ini').val();
                                                        fetch('http://localhost:8080/dsaApp/tienda/comprar/' + productos[index].nombre + '/' + username, {
                                                        method: 'GET',
                                                        headers:{
                                                                  'Content-Type':'application/json'
                                                                  },
                                                        })
                                                        .then(response => response.json())
                                                        .then(data => {
                                                                console.log(data);
                                                                if(data.username == username){
                                                                      mostrarAlerta('success','Objeto comprado');
                                                                      sessionStorage.setItem('eurillos', data.eurillos);
                                                                      ponerSaldo();
                                                                }
                                                                else{
                                                                       console.error('Error', error);
                                                                       mostrarAlerta('danger','No se ha podido comprar el objeto, no tienes eurillos suficientes');
                                                                }
                                                         }).catch(error => {
                                                                     console.error('Error', error);
                                                                     mostrarAlerta('danger', 'No se ha podido comprar el objeto, no tienes eurillos suficientes');
                                                                 });
                                                 });
                                          })
                                      .catch(error => console.error('Error al obtener la lista', error));







          }


  });
  $('#info_buton').click(function(){
            var saldo=sessionStorage.getItem('eurillos');
            $("#eurillos-miperfil").html('<p>Tu saldo es: '+saldo+'$</p>');
            var usuario=sessionStorage.getItem('username');
            $("#usuario-miperfil").html('<p>Tu usuario es: '+usuario+'</p>');
            var mail=sessionStorage.getItem('mail');
            $("#mail-miperfil").html('<p>Tu mail es: '+mail+'</p>');
            var contra=sessionStorage.getItem('password');
            $("#contra-miperfil").html('<p>Tu contraseña es: '+contra+'</p>');
            var avatar=sessionStorage.getItem('avatar');
            $("#avatar-miperfil").html('<p>Tu avatar es: '+avatar+'</p>');
            var points=sessionStorage.getItem('points');
            $("#points-miperfil").html('<p>Tus puntos son: '+points+'</p>');

  });
});