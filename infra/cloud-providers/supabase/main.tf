import {
  to = supabase_project.production
  id = var.linked_project_ref
}

resource "supabase_project" "production" {
  organization_id   = "wiywwzzpkeoblpauckif"
  name              = "bp-alternatica-skilling"
  database_password = "alternatica-skilling"
  region            = "us-east-2"            

  lifecycle {
    ignore_changes = [
      database_password, 
      name,              
      organization_id,   
      region             
    ]
  }
}

resource "supabase_settings" "production_api_settings" {
  project_ref = var.linked_project_ref
  api = jsonencode({
    db_schema            = "public,storage,graphql_public"
    db_extra_search_path = "public,extensions"
    max_rows             = 1000
  })
}