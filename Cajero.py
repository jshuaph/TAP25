from Monedas import Moneda

class Cajero():
    def __init__(self):
        self.saldo = 0
        self.moneda = Moneda()

    def desglose(self):
        return self.moneda.desglosar()

if __name__ == '__main__':
    cajero = Cajero()
    cajero.desglose()