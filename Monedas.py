class Moneda():
    def desglosar(self):
        monedas = [500, 200, 100, 50, 20, 10, 5, 2, 1]
        while True:
            try:
                cambio = int(input("Ingrese la cantidad a desglosar en monedas (0 para salir): "))
                if cambio == 0:
                    print("Fin del desglose.")
                    break
                if cambio < 0:
                    print("Por favor, ingrese un número positivo.")
                    continue
                for moneda in monedas:
                    num = cambio // moneda
                    if num > 0:
                        print(f"{num} billete(s) de {moneda}")
                        cambio -= num * moneda
            except ValueError:
                print("Entrada inválida. Por favor, ingrese un número entero.")