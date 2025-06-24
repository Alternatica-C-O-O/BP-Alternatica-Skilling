#!/bin/bash

echo "Aplicando los cambios de Terraform para Supabase..."
cd "$(dirname "$0")./cloud-providers/supabase" || exit

export TF_VAR_linked_project_ref="jtnsigjcpxhkxhpbotkv"

terraform apply -var="linked_project_ref=${TF_VAR_linked_project_ref}" \
                -auto-approve

if [ $? -eq 0 ]; then
    echo "Terraform aplicado en Supabase"
else
    echo "Terraform no fue aplicado en Supabase"
    exit 1
fi