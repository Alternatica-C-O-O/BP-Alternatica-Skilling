#!/bin/bash
echo "Iniciando Terraform para Supabase..."
cd "$(dirname "$0")./cloud-providers/supabase" || exit
terraform init
if [ $? -eq 0 ]; then
    echo "Terraform se inicializó en Supabase"
else
    echo "Terraform falló en inicializar en Supabase"
    exit 1
fi