#!/bin/bash
kubectl logs -f `kubectl get po | grep bright-kitchen | grep -v web | awk '{print $1}'`

