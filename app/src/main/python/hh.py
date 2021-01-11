from lbcapi3 import api

def Auth(hmac_key, hmac_secret):

    conn = api.hmac(hmac_key, hmac_secret)
    a = conn.call('GET', '/api/myself/').json()
    print('print----' + str(a))
    return a
