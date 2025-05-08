# -Challenge-ONE---Java---Back-end
# Conversor de Monedas 💰

Un conversor de monedas en Java que obtiene tasas de cambio en tiempo real desde una API externa, 
implementando principios SOLID y manejo robusto de errores.

## Características principales 🚀
- Interfaz de línea de comandos intuitiva
- Tasas de cambio actualizadas via API
- Conversiones entre USD, EUR, COP , BRL y ARS
- Manejo robusto de errores y validaciones
- Arquitectura limpia siguiendo principios SOLID
- Fácil extensión para nuevas funcionalidades

## Requisitos previos 📋
- Java 11 o superior
- Maven 3.6+
- API Key de [ExchangeRate-API](https://www.exchangerate-api.com/)

## Instalación y ejecución ⚙️

1. Clonar repositorio:
```
  git clone https://github.com/AmilcarCode/-Challenge-ONE---Java---Back-end.git
```

## Configurar API Key:
En ApiExchangeRateProvider.java
```
private static final String API_URL = "https://v6.exchangerate-api.com/v6/TU_API_KEY/latest/USD";
```

## Compilar y ejecutar:
```
mvn clean package
java -jar target/conversor-monedas.jar
```

## Uso 🖥️
- Seleccione una opción del menú principal    
- Ingrese el monto a convertir    
- Vea el resultado formateado    
- Repita o salga del programa

Ejemplo de flujo:
```
=== Sea bienvenido/a al Conversor de Moneda ===
    1. USD a EUR
    2. USD a COP
    3. USD a BRL
    4. EUR a USD
    5. Salir
        Elija una opción válida: 1
        Ingrese el valor a convertir: 100
        Valor convertido: 93.50€
```
## Estructura del proyecto 📁
```
Archivo                                 Clase Responsabilidad
Main.java	                        Punto de entrada de la aplicación
ExchangeRateProvider.java	        Interfaz para proveedores de tasas
ApiExchangeRateProvider.java	        Implementación concreta de la API
CurrencyConverter.java	                Lógica de conversión de divisas
MenuManager.java	                Gestión de la interfaz de usuario
ExchangeRateException.java	        Manejo de errores específicos del dominio
```

## Principios SOLID aplicados 🏛️
- Single Responsibility: Cada clase tiene una única responsabilidad clara
- Open/Closed: Fácil extensión con nuevos proveedores de tasas
- Liskov Substitution: Proveedores intercambiables vía interfaz
- Interface Segregation: Interfaces específicas y minimalistas
- Dependency Inversion: Dependencias inyectadas via constructor

## Manejo de errores ⚠️
- Validación de entrada de usuario
- Control de errores de conexión API
- Verificación de divisas faltantes
- Formateo consistente de números
- Mensajes de error descriptivos

## Mejoras futuras 🔮
- Añadir más pares de conversión
- Soporte para conversión histórica
- Implementar tests unitarios
- Crear interfaz gráfica (GUI)
- Agregar modo batch para múltiples conversiones
