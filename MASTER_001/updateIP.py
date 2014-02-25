# Jonas Margraf
# jmargraf@berklee.edu

# *** updateIP.py ***
# gets local network address (IP) and writes it to mpe_config.xml

import socket
import xml.etree.ElementTree as ET

# get IP address
IP = socket.gethostbyname(socket.gethostname())
# import xml file to Python
tree = ET.parse('mpe_config.xml')
# search xml file for the <ip> tag, then replace it's value with current IP address
elems = tree.findall('server/ip')
for elem in elems:
	elem.text = IP
# write out the updated xml file
tree.write('mpe_config.xml')

print IP