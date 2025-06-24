terraform {
  required_providers {
    supabase = {
      source = "supabase/supabase"
      version = "~> 1.0"
    }
  }
  required_version = ">= 1.0.0"
}

provider "supabase" {
  access_token = file("${path.module}/access-token")
}
