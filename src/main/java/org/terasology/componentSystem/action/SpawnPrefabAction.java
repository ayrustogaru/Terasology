/*
 * Copyright 2012
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.terasology.componentSystem.action;

import org.terasology.components.LocationComponent;
import org.terasology.components.actions.SpawnPrefabActionComponent;
import org.terasology.entitySystem.EntityManager;
import org.terasology.entitySystem.EntityRef;
import org.terasology.entitySystem.EventHandlerSystem;
import org.terasology.entitySystem.ReceiveEvent;
import org.terasology.events.ActivateEvent;
import org.terasology.game.CoreRegistry;

import javax.vecmath.Quat4f;

/**
 * @author Immortius
 */
public class SpawnPrefabAction implements EventHandlerSystem{

    private EntityManager entityManager;

    @Override
    public void initialise() {
        entityManager = CoreRegistry.get(EntityManager.class);
    }

    @ReceiveEvent(components = SpawnPrefabActionComponent.class)
    public void onActivate(ActivateEvent event, EntityRef entity) {
        SpawnPrefabActionComponent spawnInfo = entity.getComponent(SpawnPrefabActionComponent.class);
        if (spawnInfo.prefab != null) {
            EntityRef newEntity = entityManager.create(spawnInfo.prefab);
            LocationComponent loc = newEntity.getComponent(LocationComponent.class);
            if (loc != null) {
                loc.setWorldPosition(event.getLocation());
                // TODO: Set rotation
                newEntity.saveComponent(loc);
            }
        }
    }
}
