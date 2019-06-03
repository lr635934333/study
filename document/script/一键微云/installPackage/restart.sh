#!/bin/bash
python /var/lib/restful/common/src/installApp.py uninstall bright-kitchen
list=`ls images`
for image in $list;
do
    python /var/lib/restful/common/src/installApp.py loadimage -f images/${image}
done
python /var/lib/restful/common/src/installApp.py createAll bright-kitchen
