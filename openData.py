import numpy as np
import pandas as pd
import requests
import json
from flask import Flask 

app = Flask(__name__)

data = "https://opendata.paris.fr/api/records/1.0/search/?dataset=prises_electriques_vehicules_tps_reel&facet=static_name&facet=plugs_outletmodel&facet=plugs_status&facet=plugs_orientation&facet=static_brand&facet=static_owner&facet=geolocation_postalcode&facet=geolocation_city"
JSONContent = requests.get(data).json()
content = json.dumps(JSONContent, indent = 4, sort_keys=True)
/*
data = pandas.read_csv('prises_electriques_vehicules_tps_reel.csv', sep=';')
df = pandas.DataFrame(data, columns=data.keys())
data_dict = df.to_dict(orient="records")
with open('prises_electriques_vehicules_tps_reel.csv.json', "w") as f:
	json.dump(data_dict, f, indent=4)
*/

@app.route('/', methods=['GET'])
def get_data():
    return content()

if __name__ == '__main__':
    app.run()
