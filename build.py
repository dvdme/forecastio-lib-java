import os
from shutil import copyfile

version = ''
cur_dir = os.getcwd()

os.system('mvn clean')
os.system('mvn package')

os.chdir('target')

jar_files = [f for f in os.listdir('.') if f.endswith('.jar')]

for jar_file in jar_files:
    os.system('gpg --armour --detach-sign ' + jar_file)
    if jar_file.endswith('sources.jar'):
        try:
            version = jar_file.split('-')[1]
        except:
            pass        

pom_file = 'ForecastIOLib' + version + '.pom'
copyfile('../pom.xml', pom_file)
os.system('gpg --armour --detach-sign ' + pom_file)
