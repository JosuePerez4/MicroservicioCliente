import React, { useState } from 'react';

const RegistroCliente = () => {
    // 1. Estado para guardar lo que el usuario escribe
    const [formData, setFormData] = useState({
        nombre: '',
        email: '',
        password: ''
    });
    const [mensaje, setMensaje] = useState('');

    // 2. Función para manejar los cambios en los inputs
    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    // 3. Función asincrónica para enviar el POST a Spring Boot
    const handleSubmit = async (e) => {
        e.preventDefault(); // Evita que la página recargue

        try {
            const response = await fetch('http://localhost:8080/api/clientes/registro', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData) // Convertimos el objeto a JSON
            });

            if (response.ok) {
                const data = await response.json();
                setMensaje(`¡Éxito! Cliente ${data.nombre} creado con ID: ${data.id}`);
                // Limpiamos el formulario
                setFormData({ nombre: '', email: '', password: '' });
            } else {
                setMensaje('Error al registrar el cliente');
            }
        } catch (error) {
            console.error("Error de conexión:", error);
            setMensaje('Error de conexión con el servidor');
        }
    };

    // 4. El formulario visual
    return (
        <div style={{ maxWidth: '400px', margin: '0 auto', padding: '20px' }}>
            <h2>Registro de Nuevo Cliente</h2>
            <form onSubmit={handleSubmit}>
                <div style={{ marginBottom: '15px' }}>
                    <label>Nombre:</label><br />
                    <input
                        type="text"
                        name="nombre"
                        value={formData.nombre}
                        onChange={handleChange}
                        required
                        style={{ width: '100%', padding: '8px' }}
                    />
                </div>

                <div style={{ marginBottom: '15px' }}>
                    <label>Email:</label><br />
                    <input
                        type="email"
                        name="email"
                        value={formData.email}
                        onChange={handleChange}
                        required
                        style={{ width: '100%', padding: '8px' }}
                    />
                </div>

                <div style={{ marginBottom: '15px' }}>
                    <label>Contraseña:</label><br />
                    <input
                        type="password"
                        name="password"
                        value={formData.password}
                        onChange={handleChange}
                        required
                        style={{ width: '100%', padding: '8px' }}
                    />
                </div>

                <button type="submit" style={{ padding: '10px 20px', backgroundColor: '#007bff', color: 'white', border: 'none', borderRadius: '4px' }}>
                    Registrar Cliente
                </button>
            </form>

            {mensaje && <p style={{ marginTop: '20px', fontWeight: 'bold' }}>{mensaje}</p>}
        </div>
    );
};

export default RegistroCliente;